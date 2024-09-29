# SecureNote
A place where you put those links you might need for later

![Home Page](./screenshots/home-page.png)

## How It's Made:

Tech used: 
- **Java: 17**
- **OAuth2.0** 
- **SpringBoot: 3** 
- **Thymeleaf**

The project uses the current industry standard OAuth2.0 for Authorization currently supporting both Google and GitHub
as Authorization providers. In short users to need to create a password to access and use the application.

### Installation
To install and run this project on your local machine follow the following steps, make sure not to skip steps.

**Requirements**
- ```Docker``` is installed and running
- No running instance of ```Postgress``` 
- patience

Steps
```shell
# Clone project
git clone https://github.com/NatnaelSisay/SecureLinks.git
cd SecureLinks
```
```shell
# create .env file, copy the content of example.env and paste them in .env file
# this steps is added to create friction, to make sure you are serious about trying the app
touch .env
cat example.env >> .env
```

```shell
# Script to build jar file
# chmod +x script.sh
source script.sh
```

```shell
# Running application
docker-compose up
```

If everything goes successfully without an error
Visit [localhost:8080](http://localhost:8080)



## Optimizations
- Optimized sorting for Items, to show recently updated to the top
- Use Thymeleaf-layout to structure the page and reduce duplication
- Deploy application on AWS(EC2 or ECS/Farget)

## Lessons Learned:

Learning how OAuth2 works and the difference between OIDC (OpenID Connect) and OAuth was an 
eye-opening experience. For future reference, Google provides OIDC, while GitHub does not.
Knowing that will enable us to customize our support for different authorization servers.

## Examples:

[Portfolio](https://www.natnael.link)


