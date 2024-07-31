const questions = [
    { question: "¿Cuánto es 2 + 2?", answers: ["3", "4", "5", "6"], correct: "4" },
    { question: "¿Cuánto es 5 - 3?", answers: ["2", "3", "4", "1"], correct: "2" },
    { question: "¿Cuánto es 3 x 3?", answers: ["6", "9", "12", "15"], correct: "9" },
    { question: "¿Cuánto es 8 / 2?", answers: ["2", "4", "8", "16"], correct: "4" },
];

let currentQuestionIndex = 0;

function loadQuestion() {
    const questionElement = document.getElementById('question');
    const optionsElement = document.getElementById('options');

    questionElement.textContent = questions[currentQuestionIndex].question;
    optionsElement.innerHTML = '';

    questions[currentQuestionIndex].answers.forEach(answer => {
        const button = document.createElement('button');
        button.textContent = answer;
        button.onclick = () => checkAnswer(answer);
        optionsElement.appendChild(button);
    });
}

function checkAnswer(answer) {
    if (answer === questions[currentQuestionIndex].correct) {
        alert('¡Correcto!');
    } else {
        alert('Incorrecto, la respuesta correcta es ' + questions[currentQuestionIndex].correct);
    }
    document.getElementById('next-btn').style.display = 'block';
}

function nextQuestion() {
    currentQuestionIndex++;
    if (currentQuestionIndex < questions.length) {
        loadQuestion();
        document.getElementById('next-btn').style.display = 'none';
    } else {
        alert('¡Felicidades, has completado el quiz!');
    }
}

window.onload = loadQuestion;
