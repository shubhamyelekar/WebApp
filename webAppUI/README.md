# WebappUi

## Development server
Run `npm i` and then `npm start` to start the UI

## Running unit tests
Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

# Login
Login component has field validation which allows only valid email addresses and passwords.
UserService is mock service to validate user login. It returns valid for usernames in the list ['abc@gmail.com', 'xyz@gmail.com', 'test@gmail.com']. For rest it will block the user.

# Home
Nothing much is mentioned for home component, so didn't add much things there. A welcome message is shown along with a logout button.
