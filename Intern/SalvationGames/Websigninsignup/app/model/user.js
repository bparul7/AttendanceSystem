const validator = require ('validator')
const mongoose = require ('mongoose')
const bcrypt = require ('bcrypt')
//Validator Functions : https://www.npmjs.com/package/validator
//Mongoose Validator Schema Types : https://mongoosejs.com/docs/schematypes.html
//make model to store in database
const userSchema = new mongoose.Schema ({
	name : {
		type : String,
		required : true,
		trim : true,
		lowercase : true,
		validate (value) {
			if (value.length < 5)
				throw new Error ('String Length should be greater than 4')
		}
	},
	email : {
		type : String,
		unique : true,
		required : true,
		trim : true,
		lowercase : true,
		validate (value) {
			if (!validator.isEmail(value))
				throw new Error ('Email not valid')
		}
	},
	password : {
		type : String,
		required : true,
		trim : true,
		validate (value) {
			if (value.length <= 6)
				throw new Error ('Password Too Short')
			if (value.toLowerCase().includes("password"))
				throw new Error ("Try some Other Password")
		}
	}
})

userSchema.statics.loginCredentials = async (email, password) => {
	const user = await User.findOne ({email : email});
	if (!user) {
		throw new Error ("Unable to Login");
	}
	const isMatch = await bcrypt.compare (password , user.password);
	if (!isMatch) {
		throw new Error ("Unable to Login");
	}
	return user;
}

userSchema.pre ('save', async function (next) {
	const user = this;
	if (!user.isModified (user.password)) {
		user.password = await bcrypt.hash (user.password, 8);
	}
})

const User = mongoose.model ('User', userSchema)

module.exports = User