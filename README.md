# spring-mvc-heroku
A Spring MVC application, which can easily be deployed to Heroku.

This application was created following the [Getting Started with Java on Heroku](https://devcenter.heroku.com/articles/getting-started-with-java) article - check it out.

## Running Locally

Make sure you have **Java** and **Maven** installed.  Also, install the [Heroku Toolbelt](https://toolbelt.heroku.com/).

```sh
$ git clone https://github.com/gsag/spring-mvc-heroku.git
$ cd spring-mvc-heroku
$ sh run.sh
```

Your app should now be running on [localhost:9090](http://localhost:9090/).

Ensure you have a local PostgreSQL instance installed for development tests.

Database configurations can be changed at resources/**database.properties**.

## Deploying to Heroku

```sh
$ heroku create spring-app-name
$ git push heroku master
$ heroku open
```
Besides, you can configure automatic deploys by using a Github repository branch. See the [Github Integration](https://devcenter.heroku.com/articles/github-integration) article.

## Documentation

For more information about using Java on Heroku, see these Dev Center articles:

- [Java on Heroku](https://devcenter.heroku.com/categories/java)
