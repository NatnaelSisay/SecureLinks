# SecureNote
A place where you put those links you might need for later

![Home Page](./screenshots/home-page.png)

## How It's Made:

**Tech used:** OAuth2.0, SpringBoot, Thymeleaf

The project uses the current industry standard OAuth2.0 for Authorization currently supporting both Google and GitHub
as Authorization providers. In short users to need to create a password to access and use the application.

## Optimizations
- Optimized sorting for Items, to show recently updated to the top
- Deploy application on AWS(EC2 or ECS/Farget)

## Lessons Learned:

Learning how OAuth2 works and the difference b/n OIDC(Open Id connect) and OAuth was eye opening experience. For future reference
Google provides OIDC and GitHub don't provide that, knowing that will enable us to customize our support for different authorization servers.

## Examples:
Take a look at these couple examples that I have in my own portfolio:

**Palettable:** https://github.com/alecortega/palettable

**Twitter Battle:** https://github.com/alecortega/twitter-battle

**Patch Panel:** https://github.com/alecortega/patch-panel


