<?php
$host = 'db';
$db = 'my_database';
$user = 'root';
$password = 'rootpassword';

$conn = new mysqli($host, $user, $password, $db);

if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

$result = $conn->query("SELECT username, email FROM users");

while ($row = $result->fetch_assoc()) {
    echo "Usuario: " . $row['username'] . " - Email: " . $row['email'] . "<br>";
}

$conn->close();
?>