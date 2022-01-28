# stockdisplay-accelerator
Stock Infortmation Display project that is built for Tanzu App Accelerator.

The project is written in Java, uses SpringBoot reactive programming approach.
It uses a FibHub free library to pull stock price information.

## How to run the application
The application need stockdisplay.client.finHub.token property to be set fibhub-api-token.

You can utilize
application.propeties or application.yaml file
```
stockdisplay.client.finHub.token=<fibhub-api-token>
```
or environemnt variables

