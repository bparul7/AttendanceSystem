const express = require ('express')
const route = new express.Router();
const User = require ('../model/user.js')

//save data
route.post ('/users', async (req, res) => {
	const me = new User (req.body)
	try {
		await me.save ();
		res.status (201).send (me);
	}
	catch (e) {
		res.status (500).send ("Some error occured while saving in database:)")
	}
})

//login users
route.post ('/users/login', async(req, res) => {
	try {
		const user = await User.loginCredentials (req.body.email, req.body.password);
		res.send (user);
	}
	catch (e) {
		res.status (400).send ("Unable to Login");
	}
})

module.exports = route