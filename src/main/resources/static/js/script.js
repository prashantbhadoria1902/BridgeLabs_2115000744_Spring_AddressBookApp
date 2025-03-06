const apiUrl = "http://localhost:8080/api/contacts";

// Function to fetch and display contacts
async function fetchContacts() {
    const response = await fetch(apiUrl);
    const contacts = await response.json();
    const contactList = document.getElementById("contactList");
    contactList.innerHTML = "";

    contacts.forEach(contact => {
        const li = document.createElement("li");
        li.innerHTML = `${contact.name} - ${contact.phone} - ${contact.email} 
                        <button onclick="deleteContact(${contact.id})">Delete</button>`;
        contactList.appendChild(li);
    });
}

// Function to add a new contact
document.getElementById("contactForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    const name = document.getElementById("name").value;
    const phone = document.getElementById("phone").value;
    const email = document.getElementById("email").value;

    const response = await fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name, phone, email })
    });

    if (response.ok) {
        fetchContacts(); // Refresh list
        document.getElementById("contactForm").reset();
    }
});

// Function to delete a contact
async function deleteContact(id) {
    await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
    fetchContacts(); // Refresh list
}

// Load contacts on page load
fetchContacts();
