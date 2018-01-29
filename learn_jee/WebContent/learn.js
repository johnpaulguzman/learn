/* These */// are comments
var str_data = "eow" + " world";
console.log(str_data);
var num_data = 2018 / 5.2;
console.log(num_data);
var bool_data = true && !false && (false || true);
console.log(bool_data);

var i = 3;
if (i == 1) {
	console.log("im 1");
} else if (i == 2) {
	console.log("im 2");
} else {
	console.log("im neither 1 nor 2");
}

var color = "red";
switch (color) {
case "blue":
	console.log("This is blue.");
	break;
case "red":
	console.log("This is red.");
	break;
default:
	console.log("Color not recognized.");
}

for (i = 1; i <= 3; i++) {
	console.log(i + " im a for loop");
}

var i = 0;
while (i <= 3) {
	console.log(i + "im a while loop");
	i++;
}

function sayHello(name, age) {
	console.log(name + " is " + age + " years old.");
	return "im a return";
}

console.log(sayHello("John", 20));

var user = prompt("Please enter your name");
alert("Hi " + user);
var result = confirm("Is this your real name?");
if (!result) {
	console.log("no good liar!")
}

function bornYear() {
	return 2018 - this.age;
}

function person(name, age) {
	this.name = name;
	this.age = age;
	this.yearOfBirth = bornYear;
}

var p = new person("A", 22);
console.log(p.name);
console.log(p['age']);
console.log(p['yearOfBirth']());

var arr = new Array("HTML", "CSS", "JS");
arr = arr.concat(["Python", ]);
console.log(arr);

var dict = [];
dict["name"] = "John";
dict["age"] = 46;
console.log(dict["age"]);

// DO DOM https://www.sololearn.com/Play/JavaScript