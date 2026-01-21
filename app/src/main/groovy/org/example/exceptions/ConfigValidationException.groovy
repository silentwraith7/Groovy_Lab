package org.example.exceptions

class ConfigValidationException extends Exception {

    ConfigValidationException() {
        super('Missing required fields in rules')
    }

}
