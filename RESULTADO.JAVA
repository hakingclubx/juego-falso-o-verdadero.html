document.addEventListener("DOMContentLoaded", () => {
    const questionContainer = document.getElementById("questionContainer");
    const resultContainer = document.getElementById("resultContainer");
    const questionElement = document.getElementById("question");
    const trueButton = document.getElementById("trueButton");
    const falseButton = document.getElementById("falseButton");
    const resultMessage = document.getElementById("resultMessage");
    const nextButton = document.getElementById("nextButton");
    const questionNumberElement = document.getElementById("questionNumber");
    const totalQuestionsElement = document.getElementById("totalQuestions");
    const timerElement = document.getElementById("timer");

    const quizData = [
        {
            question: "El cielo es azul.",
            answer: true
        },
        {
            question: "El océano Atlántico es el océano más grande del mundo.",
            answer: false
        },
        {
            question: "Los perros son mamíferos.",
            answer: true
        }
    ];

    let currentQuestionIndex = 0;
    let score = 0;
    let timer;
    let timeTaken;

    function startTimer() {
        timeTaken = 0;
        timer = setInterval(() => {
            timeTaken++;
            timerElement.textContent = timeTaken;
        }, 1000);
    }

    function stopTimer() {
        clearInterval(timer);
    }

    function showQuestion() {
        questionContainer.style.display = "block";
        resultContainer.style.display = "none";
        const currentQuestion = quizData[currentQuestionIndex];
        questionElement.textContent = currentQuestion.question;
        questionNumberElement.textContent = currentQuestionIndex + 1;
        totalQuestionsElement.textContent = quizData.length;
        timerElement.textContent = "0";
        startTimer();
    }

    function checkAnswer(userAnswer) {
        stopTimer();
        const currentQuestion = quizData[currentQuestionIndex];
        if (userAnswer === currentQuestion.answer) {
            score++;
            resultMessage.textContent = `¡Correcto! Tiempo de respuesta: ${timeTaken} segundos.`;
        } else {
            resultMessage.textContent = `Incorrecto. Tiempo de respuesta: ${timeTaken} segundos.`;
        }
        questionContainer.style.display = "none";
        resultContainer.style.display = "block";
    }

    function nextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < quizData.length) {
            showQuestion();
        } else {
            showFinalScore();
        }
    }

    function showFinalScore() {
        questionContainer.style.display = "none";
        resultContainer.style.display = "block";
        resultMessage.textContent = `Juego terminado. Tu puntaje es ${score} de ${quizData.length}.`;
        nextButton.style.display = "none";
    }

    trueButton.addEventListener("click", () => checkAnswer(true));
    falseButton.addEventListener("click", () => checkAnswer(false));
    nextButton.addEventListener("click", nextQuestion);

    showQuestion();
});
