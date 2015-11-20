echo "==============================="
echo "Fazendo deploy do projeto..."
echo "==============================="
echo ""

heroku login
#Descomente se ainda não criou um app no heroku
#heroku create spring-mvc-3120
git push heroku master

heroku open
