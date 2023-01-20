// To parse this JSON data, do
//
//     final reqLogin = reqLoginFromMap(jsonString);

import 'dart:convert';

ReqLogin? reqLoginFromMap(String str) => ReqLogin.fromMap(json.decode(str));

String reqLoginToMap(ReqLogin? data) => json.encode(data!.toMap());

class ReqLogin {
  ReqLogin({
    this.username,
    this.password,
  });

  String? username;
  String? password;

  factory ReqLogin.fromMap(Map<String, dynamic> json) => ReqLogin(
    username: json["username"],
    password: json["password"],
  );

  Map<String, dynamic> toMap() => {
    "username": username,
    "password": password,
  };
}
