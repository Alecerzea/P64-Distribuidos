const express = require('express');
const app = express();
const port = 3000;

let questions = [
    { question: "¿Cuánto es 2 + 2?", answers: ["3", "4", "5", "6"], correct: "4" },
    { question: "¿Cuánto es 5 - 3?", answers: ["2", "3", "4", "1"], correct: "2" },
    { question: "¿Cuánto es 3 x 3?", answers: ["6", "9", "12", "15"], correct: "9" },
    { question: "¿Cuánto es 8 / 2?", answers: ["2", "4", "8", "16"], correct: "4" },
];

app.use(express.static('public'));

app.get('/questions', (req, res) => {
    res.json(questions);
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});
