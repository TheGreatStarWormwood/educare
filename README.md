

# Dependencies

**node js** server requirements
`npm install express mysql2 ejs body-parser cors path`

a `mysql` server instance, in our testing we ran it inside a docker container. 

# Setting up server environment

to simulate a database system, run the script 
`create-tables.sql`
inside an `sql` server instance
this creates the necessary tables and fills them with sample data.

in `CONSTANTS.js` file you have to specify the database information
sample information:

```
// CONSTANTS.js

module.exports = {
DB_HOST: 'localhost',
DB_USER: 'root',
DB_PASSWORD: 'root',
DB_NAME: 'educare',
DB_PORT: 3305 
};
```

# Starting server

Server can be started by running 
`node app.js`
located at the project root

the server port is by default `4405`
can be changed at the top of app.js

```
// alter as necessary
const port = 4405; 
```

# Browsing the website

to browse, using any browser navigate to

`localhost:{PORT}/`

this will take you to the home page, however if you are not logged in you will be redirected to the login page where you can choose any `tutor` or `student` user to simulate the browsing experience