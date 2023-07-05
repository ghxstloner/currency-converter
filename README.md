# Currency Converter 

The Currency Converter in Java is an application that allows you to convert monetary values between different currencies using the JavaFX API for the user interface and the real-time API from OpenExchangeRates to retrieve conversion data.

## Features

-   Intuitive and user-friendly graphical interface.
-   Conversion of monetary values between different currencies.
-   Utilization of real-time data provided by OpenExchangeRates.
-   Automatic update of exchange rates.

## Requirements

-   Java Development Kit (JDK) 17 or higher.
-   Internet connection.

## Installation

1.  Download the source code of the Currency Converter from the [GitHub repository](https://github.com/ghxstloner/currency-converter).
2.  Open the project in your preferred development environment (e.g., IntelliJ IDEA or Eclipse).
3.  Configure the necessary dependencies for the JavaFX API.
4.  Compile and run the application.

## Usage

1.  Run the application.
2.  In the user interface, select the source currency and the target currency from the respective dropdown fields.
3.  Enter the monetary value you want to convert in the input field.
4.  Click the "Convert" button.
5.  The conversion result will be displayed in the output field.

## OpenExchangeRates API Configuration

1.  Sign up on [OpenExchangeRates](https://openexchangerates.org/) to obtain an API key.
2.  In the application source code, locate the OpenExchangeRates API configuration section.
3.  Replace `YOUR_API_KEY` with your API key obtained in the previous step.
```Java
`// OpenExchangeRates API Configuration
private final String apiKey = "YOUR_API_KEY";
```
## Contribution

If you want to contribute to the project, you can follow these steps:

1.  Fork the repository.
2.  Create a branch for your new feature (`git checkout -b new-feature`).
3.  Make the changes and commit them (`git commit -am 'Add new feature'`).
4.  Push your changes to the remote repository (`git push origin new-feature`).
5.  Open a pull request on GitHub.

## Credits

-   Author: [Yoiner Moreno](https://github.com/ghxstloner/currency-converter)
