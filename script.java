// Import Firebase modules
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.20.0/firebase-app.js";
import { getFirestore, collection, addDoc, getDocs } from "https://www.gstatic.com/firebasejs/9.20.0/firebase-firestore.js";

// Firebase configuration
const firebaseConfig = {
  apiKey: "YOUR_API_KEY",
  authDomain: "YOUR_PROJECT_ID.firebaseapp.com",
  projectId: "YOUR_PROJECT_ID",
  storageBucket: "YOUR_PROJECT_ID.appspot.com",
  messagingSenderId: "YOUR_MESSAGING_SENDER_ID",
  appId: "YOUR_APP_ID"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const db = getFirestore(app);

// Save Notes to Firestore
async function saveNote() {
  const noteInput = document.getElementById("noteInput").value;
  if (noteInput.trim()) {
    try {
      await addDoc(collection(db, "notes"), { content: noteInput, timestamp: new Date() });
      alert("Note saved successfully!");
      document.getElementById("noteInput").value = "";
      loadNotes();
    } catch (error) {
      console.error("Error saving note: ", error);
    }
  } else {
    alert("Please write something!");
  }
}

// Load Notes from Firestore
async function loadNotes() {
  const notesList = document.getElementById("notesList");
  notesList.innerHTML = "";

  const querySnapshot = await getDocs(collection(db, "notes"));
  querySnapshot.forEach((doc) => {
    const listItem = document.createElement("li");
    listItem.textContent = doc.data().content;
    notesList.appendChild(listItem);
  });
}

// Generate QR Code
function generateQRCode() {
  const qrInput = document.getElementById("qrInput").value;
  const qrOutput = document.getElementById("qrOutput");

  qrOutput.innerHTML = ""; // Clear previous QR code
  QRCode.toCanvas(qrInput, { width: 200 }, (error, canvas) => {
    if (error) console.error(error);
    qrOutput.appendChild(canvas);
  });
}

// Generate Token
function generateToken() {
  const token = uuidv4(); // Generates a unique token
  document.getElementById("tokenOutput").textContent = `Generated Token: ${token}`;
}