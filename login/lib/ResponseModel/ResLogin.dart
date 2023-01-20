// To parse this JSON data, do
//
//     final resLogin = resLoginFromMap(jsonString);

import 'dart:convert';

ResLogin? resLoginFromMap(String str) => ResLogin.fromMap(json.decode(str));

String resLoginToMap(ResLogin? data) => json.encode(data!.toMap());

class ResLogin {
  ResLogin({
    this.id,
    this.username,
    this.email,
    this.firstName,
    this.lastName,
    this.gender,
    this.image,
    this.token,
  });

  int? id;
  String? username;
  String? email;
  String? firstName;
  String? lastName;
  String? gender;
  String? image;
  String? token;

  factory ResLogin.fromMap(Map<String, dynamic> json) => ResLogin(
    id: json["id"],
    username: json["username"],
    email: json["email"],
    firstName: json["firstName"],
    lastName: json["lastName"],
    gender: json["gender"],
    image: json["image"],
    token: json["token"],
  );

  Map<String, dynamic> toMap() => {
    "id": id,
    "username": username,
    "email": email,
    "firstName": firstName,
    "lastName": lastName,
    "gender": gender,
    "image": image,
    "token": token,
  };
}
