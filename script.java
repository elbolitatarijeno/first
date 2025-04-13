import { initializeApp } from "https://www.gstatic.com/firebasejs/11.6.0/firebase-app.js";
import { getFirestore, collection, addDoc, onSnapshot } from "https://www.gstatic.com/firebasejs/11.6.0/firebase-firestore.js";
import QRCode from "https://cdn.jsdelivr.net/npm/qrcode/build/qrcode.min.js";
import { v4 as uuidv4 } from "https://cdn.jsdelivr.net/npm/uuid/dist/umd/uuidv4.min.js";

// Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyBxoxYAOC1EGZD5zcnREOeHEfJA7jdizg8",
  authDomain: "elbolitatarijeno-1fac7.firebaseapp.com",
  projectId: "elbolitatarijeno-1fac7",
  storageBucket: "elbolitatarijeno-1fac7.appspot.com",
  messagingSenderId: "468578561335",
  appId: "1:468578561335:web:cfb802852a89087b08beb2"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const db = getFirestore(app);

// Save Note Function
async function saveNote() {
  const noteInput = document.getElementById("noteInput").value;
  if (noteInput.trim()) {
    try {
      await addDoc(collection(db, "notes"), {
        content: noteInput,
        timestamp: new Date()
      });
      alert("Note saved successfully!");
      document.getElementById("noteInput").value = "";
    } catch (error) {
      console.error("Error saving note:", error);
      alert("Failed to save the note. Check the console for details.");
    }
  } else {
    alert("Please enter a note!");
  }
}

// Retrieve Notes in Real-Time
function retrieveNotes() {
  const notesList = document.getElementById("notesList");

  onSnapshot(collection(db, "notes"), (snapshot) => {
    notesList.innerHTML = "";
    snapshot.forEach((doc) => {
      const note = doc.data().content;
      const listItem = document.createElement("li");
      listItem.textContent = note;
      notesList.appendChild(listItem);
    });
  });
}

// QR Code Generator
function generateQRCode() {
  const qrInput = document.getElementById("qrInput").value;
  if (qrInput.trim()) {
    const qrOutput = document.getElementById("qrOutput");
    qrOutput.innerHTML = ""; // Clear previous QR code
    QRCode.toCanvas(qrInput, (error, canvas) => {
      if (error) console.error("QR Code error:", error);
      qrOutput.appendChild(canvas);
    });
  } else {
    alert("Please enter text for the QR code!");
  }
}

// Token Generator
function generateToken() {
  const tokenOutput = document.getElementById("tokenOutput");
  const token = uuidv4(); // Generate unique token
  tokenOutput.textContent = token;
}

// Load Notes on Page Load
window.onload = retrieveNotes;