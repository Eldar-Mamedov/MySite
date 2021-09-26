package com.epam.mysite.engine.controller.impl;


import com.epam.mysite.domain.entity.UserEntity;
import com.epam.mysite.domain.enums.Role;
import com.epam.mysite.domain.webservice.Response;
import com.epam.mysite.domain.webservice.User;
import com.epam.mysite.engine.controller.api.IUserController;
import com.epam.mysite.engine.database.repository.api.IUserRepository;
import com.epam.mysite.engine.database.repository.impl.UserRepository;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

import static com.epam.mysite.util.IParser.fromJson;
import static com.epam.mysite.util.IParser.toJson;

public class UserController implements IUserController {

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final Pattern PHONE_REGEX =
            Pattern.compile("^\\+?3?8?(0\\d{9})$");
    private static final Pattern NON_EMPTY_REGEX =
            Pattern.compile("^(?!\\s*$).+");
    private static final Pattern DIGITS_REGEX =
            Pattern.compile("\\d+");
    private static final Pattern GENDER_REGEX =
            Pattern.compile("^(male|female|prefer)$");

    private final IUserRepository userRepository = new UserRepository();
    private ResourceBundle registrationBundle;

    private User loggedUser;

    @Override
    public Response saveUser(User user, Role role) {
        registrationBundle = ResourceBundle.getBundle("registration", new Locale(System.getProperty("locale")));
        user.setPassword(user.getPassword());
        Response response = new Response();
        if (isValidForRegistration(user, response)) {
            UserEntity userEntity = fromJson(toJson(user), UserEntity.class);
            try {
                boolean result = userRepository.save(userEntity, role);
                if (result) {
                    response.setStatus(200);
                }
            } catch (Exception e) {
                response.setStatus(500);
                response.setBody(e.getMessage());
            }
        } else {
            response.setStatus(400);
        }
        return response;
    }

    @Override
    public Response login(User user) {
        ResourceBundle loginBundle = ResourceBundle.getBundle("login", new Locale(System.getProperty("locale")));
        user.setPassword(user.getPassword());
        Response response = new Response();
        if (isValidForLogin(user, response)) {
            try {
                UserEntity userEntity = userRepository.findUserByLogin(user.getLogin());
                if (userEntity != null) {
                    if (userEntity.getPassword().equals(user.getPassword())) {
                        response.setStatus(200);
                        response.setBody(loginBundle.getString("success_message"));
                        loggedUser = fromJson(toJson(userEntity), User.class);
                    } else {
                        response.setStatus(400);
                        response.setBody(loginBundle.getString("incorrect_password"));
                    }
                } else {
                    response.setStatus(400);
                    response.setBody(loginBundle.getString("user_not_found"));
                }
            } catch (SQLException e) {
                response.setStatus(500);
                response.setBody(e.getMessage());
            }
        } else {
            response.setStatus(400);
        }
        return response;
    }

    @Override
    public User getLoggedUser() {
        return loggedUser;
    }

    private boolean isValidForRegistration(User user, Response response) {
        Map<String, List<String>> errors = new HashMap<>();
        boolean result = isNotCorrectField(user.getName(), "name", errors);
        result = isNotCorrectField(user.getSurname(), "surname", errors);
        if (!PHONE_REGEX.matcher(user.getPhoneNumber()).find()) {
            result = false;
            putValue(errors, "phone", registrationBundle.getString("error_phone"));
        }
        if (!EMAIL_REGEX.matcher(user.getEmail()).find()) {
            result = false;
            putValue(errors, "email", registrationBundle.getString("error_email"));
        }
        result = isNotEmptyField(user.getLogin(), "login", errors);
        result = isNotEmptyField(user.getPassword(), "password", errors);
        if (!GENDER_REGEX.matcher(user.getGender()).find()) {
            result = false;
            putValue(errors, "gender", registrationBundle.getString("error_gender"));
        }
        if (!result) {
            response.setBody(toJson(errors));
        }
        return result;
    }

    private boolean isValidForLogin(User user, Response response) {
        Map<String, List<String>> errors = new HashMap<>();
        boolean result = isNotEmptyField(user.getLogin(), "login", errors);
        result = isNotEmptyField(user.getPassword(), "password", errors);
        if (!result) {
            response.setBody(toJson(errors));
        }
        return result;
    }

    private boolean isNotCorrectField(String value, String fieldName, Map<String, List<String>> errors) {
        boolean isCorrect = isNotEmptyField(value, fieldName, errors);
        if (DIGITS_REGEX.matcher(value).find()) {
            putValue(errors, fieldName, registrationBundle.getString("error_digits"));
            isCorrect = false;
        }
        return isCorrect;
    }

    private boolean isNotEmptyField(String value, String fieldName, Map<String, List<String>> errors) {
        if (!NON_EMPTY_REGEX.matcher(value).find()) {
            putValue(errors, fieldName, registrationBundle.getString("error_empty"));
            return false;
        }
        return true;
    }

    private void putValue(Map<String, List<String>> map, String key, String value) {
        List<String> values;
        if (map.containsKey(key)) {
            values = map.get(key);
        } else {
            values = new ArrayList<>();
        }
        values.add(value);
        map.put(key, values);
    }
}
