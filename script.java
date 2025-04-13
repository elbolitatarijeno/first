function saveNote() {
  const noteInput = document.getElementById("noteInput").value;
  const notesList = document.getElementById("notesList");

  if (noteInput.trim()) {
    const listItem = document.createElement("li");
    listItem.textContent = noteInput;
    notesList.appendChild(listItem);
    document.getElementById("noteInput").value = ""; // Clear input box
  } else {
    alert("Please write something!");
  }
}

const canvas = document.getElementById("drawingCanvas");
const ctx = canvas.getContext("2d");
let drawing = false;

canvas.addEventListener("mousedown", () => (drawing = true));
canvas.addEventListener("mouseup", () => (drawing = false));
canvas.addEventListener("mousemove", draw);

function draw(event) {
  if (!drawing) return;
  ctx.fillStyle = "black";
  ctx.beginPath();
  ctx.arc(event.offsetX, event.offsetY, 2, 0, Math.PI * 2);
  ctx.fill();
}

function updateDateTime() {
  const datetime = new Date().toLocaleString();
  document.getElementById("datetime").textContent = `Current Date and Time: ${datetime}`;
}
setInterval(updateDateTime, 1000); // Update every second

document.getElementById("fileInput").addEventListener("change", (event) => {
  const files = event.target.files;
  const filePreview = document.getElementById("filePreview");
  filePreview.innerHTML = "";

  for (const file of files) {
    const fileDiv = document.createElement("div");
    fileDiv.textContent = `Uploaded: ${file.name}`;
    filePreview.appendChild(fileDiv);
  }
});