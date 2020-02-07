package com.adtech.todolist.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class TodoConstants {

    public static final String CLIENT_ADMIN_ROLE_NAME = "Admin";
    public static final String USER_ROLE_NAME = "User";
    public static final String EMAIL_VERIFICATION_TOKEN_VALIDITY_KEY = "email_verification_token_validity";
    public static final Integer EMAIL_VERIFICATION_TOKEN_VALIDITY_VALUE = 720;   // In Hours
    public static final String RESET_PASSWORD_TOKEN_VALIDITY_KEY = "reset_password_token_validity";
    public static final Integer RESET_PASSWORD_TOKEN_VALIDITY_VALUE = 240;   // In Hours
    public static final String SETUP_ACCOUNT_TOKEN_VALIDITY_KEY = "setup_account_token_validity";
    public static final Integer SETUP_ACCOUNT_TOKEN_VALIDITY_VALUE = 240;   // In Hours

    public static final String CONTACT_US_MAILING_LIST_KEY = "contact_us_mailing_list";
    public static final String CONTACT_US_MAILING_LIST_VALUE = "developer.ad.00@gmail.com, avitdas00@gmail.com, ferozk@obsessory.com";
    public static final String CONTACT_US_CC_MAILING_LIST_KEY = "contact_us_cc_mailing_list";
    public static final String CONTACT_US_CC_MAILING_LIST_VALUE = "studyad00@gmail.com, ferozk@obsessory.com";

    public static final String ALERTS_EMAIL_ID = "alerts@adtech.com";
    public static final String CONTACT_US_SUBJECT = "New User";
    public static final String CONTACT_US_SUBJECT_MAIL_BACK = "Thank you for getting in touch!";
    public static final String REGISTRATION_ALERT_SUBJECT = "New User Registration";
    public static final String ERROR_NOTIFICATION_SUBJECT = "Error Notification";

    public static final String VERIFICATION_EMAIL_SUBJECT = "{{user}}, Please Verify Your Email";
    public static final String PASSWORD_RESET_SUBJECT = "Password Reset";
    public static final String EMAIL_VERIFICATION_SUCCESS_SUBJECT = "Thanks For Verifying Your Account";

    public static final String PASSWORD_RESET_SUCCESS_SUBJECT = "Password Reset Successful";
    public static final String PASSWORD_CHANGE_SUCCESS_SUBJECT = "Password Change Successful";
    public static final String REGISTER_REQUEST_RECEIVED_SUBJECT = "Thank you for registering with us";
    public static final String POST_APPROVAL_ACCOUNT_SETUP_SUBJECT = "Welcome to Todo List";
    public static final String ACCOUNT_SETUP_SUCCESS_SUBJECT = "Your Todo Account Is Ready";

    public static final String USER_SUBJECT_KEY_PLACEHOLDER = "{{user}}";
    public static final String ALERTS_SENDER_NAME = "Todo List Alerts";
    public static final String SIGN_UP_SUBJECT = "{{user}}, Welcome to Todo List!";

    public final static String SOURCE_HEADER_KEY = "appsource";
    public final static String SOURCE_HEADER_VALUE_DASHBOARD = "DASHBOARD";
    public final static String SOURCE_HEADER_VALUE_TWO_WAY_API = "TWO_WAY_API";
    public final static String SOURCE_HEADER_VALUE_ANDROID_APP = "ANDROID_APP";
    public final static String SOURCE_HEADER_VALUE_IOS_APP = "IOS_APP";

    public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public final static String X_FORWARDED_FOR_HEADER = "X-Forwarded-For";
    public final static String X_REAL_IP_HEADER = "X-Real-IP";

    public final static String PROPERTY_START_DATE = "startDate";
    public final static String PROPERTY_COMPLETION_DATE = "completionDate";
    public final static String PROPERTY_CREATED_DATE = "createdDate";
    public final static String PIPE_SEPARATED_REGEX = "\\|";

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 3*60*60;
    public static final String FEAS_TOKEN_HEADER_KEY = "X-Auth-Token";
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String BASIC_TOKEN_PREFIX = "Basic";

    public static final String PYTHON_PATH = "/adtech/todolist/python/env/bin/python3 ";
    public static final String PYTHON_APP = "/adtech/todolist/python/env/bin/python3 ";
    public static final String PYTHON_SCRIPTS = "/adtech/repository/todolist/src/main/python/";

    public static final String []  KYCHUB_SUPER_ADMINS_EMAIL  = {"ferozk@obsessory.com", "developer.ad.00@gmail.com"};

    public static final String NOTIFICATION_TYPE_USER_ADDED_Name  = "USER ADDED";
    public static final String NOTIFICATION_TYPE_PROFILE_UPDATED_Name  = "PROFILE UPDATED";
    public static final String NOTIFICATION_TYPE_API_KEY_GENERATED_Name  = "API KEY GENERATED";
    public static final String NOTIFICATION_TYPE_TOKEN_VALIDITY_UPDATED_Name  = "TOKEN VALIDITY UPDATED";
    public static final String NOTIFICATION_TYPE_SCHEDULE_TODO_SENT_Name  = "SCHEDULE TODO SENT";
    public static final String NOTIFICATION_TYPE_SCHEDULE_TODO_FAILED_Name  = "SCHEDULE TODO FAILED";

    public static final String NOTIFICATION_TYPE_USER_ADDED_Slug = "user-added";
    public static final String NOTIFICATION_TYPE_PROFILE_UPDATED_Slug = "profile-updated";
    public static final String NOTIFICATION_TYPE_API_KEY_GENERATED_Slug = "api-key-generated";
    public static final String NOTIFICATION_TYPE_TOKEN_VALIDITY_UPDATED_Slug = "token-validity-updated";
    public static final String NOTIFICATION_TYPE_SCHEDULE_TODO_SENT_Slug = "schedule-todo-sent";
    public static final String NOTIFICATION_TYPE_SCHEDULE_TODO_FAILED_Slug = "schedule-todo-failed";

    public static final Map<String,String> tokenMap = new HashMap<>();
}
