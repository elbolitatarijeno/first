document.addEventListener("DOMContentLoaded", () => {
  const calendar = document.getElementById("calendar");
  const currentDateElem = document.getElementById("current-date");

  // Infinite Calendar (basic example)
  function generateCalendar(month, year) {
    calendar.innerHTML = ""; // Clear old calendar
    // Logic for generating dates (simple placeholder here)
    for (let day = 1; day <= 30; day++) {
      const dayElem = document.createElement("div");
      dayElem.className = "calendar-day";
      dayElem.textContent = `${day}/${month}/${year}`;
      calendar.appendChild(dayElem);
    }
  }

  // Current Date Highlight
  const today = new Date();
  currentDateElem.textContent = today.toDateString();
  generateCalendar(today.getMonth() + 1, today.getFullYear());
});

app.post("/save-note", async (req, res) => {
  const { date, note } = req.body;
  await NotesModel.create({ date, note });
  res.status(201).send("Note saved!");
});

app.get("/get-notes", async (req, res) => {
  const { date } = req.query;
  const notes = await NotesModel.find({ date });
  res.json(notes);
});

setInterval(() => {
  const now = new Date();
  document.getElementById("current-time").textContent = now.toLocaleTimeString();
}, 1000);

