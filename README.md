# Railway Platform Management System Using Data Structures

## Overview
A Java console mini project demonstrating **Queue (FIFO)** and **Priority Queue** for organizing trains waiting for platform allocation. A small browser-based demonstration is also included in the `web` folder.

## Features
- Add trains to a normal waiting queue.
- Allocate the next train using FIFO order.
- Add trains with High, Medium, or Normal priority.
- Process the highest-priority train first.
- Display both waiting lists.
- Validate menu input and empty train details.

## Project structure
```text
Railway_Platform_Management_System/
├── ABSTRACT.md
├── README.md
├── src/
│   └── RailwayPlatformManagement.java
└── web/
    ├── index.html
    ├── style.css
    └── script.js
```

## Requirements
- Java JDK 17 or later (the code uses modern switch syntax).
- A browser for the optional web demonstration.

## Compile and run the Java program
Open a terminal in the project folder and run:

```bash
javac src/RailwayPlatformManagement.java
java -cp src RailwayPlatformManagement
```

Choose an option from the menu and follow the prompts. In the normal queue, trains are handled in arrival order. In the priority queue, priority `1` is processed before `2`, then `3`; trains with equal priority retain arrival order.

## Web demonstration
Open `web/index.html` in a modern browser. The HTML provides the page structure, CSS styles the interface, and JavaScript demonstrates queue and priority-queue behaviour in the browser. This browser demo is a separate front-end demonstration; it does not call the Java console program.

## Data structures used
- **Queue:** `LinkedList` used through Java's `Queue` interface. `offer()` inserts at the rear and `poll()` removes from the front.
- **Priority Queue:** Java `PriorityQueue`, ordered by priority and then arrival sequence.
- **Time complexity:** Queue insertion/removal are typically O(1); priority-queue insertion/removal are O(log n); displaying/sorting the priority list is O(n log n).

## Limitations
This is an academic simulation. It does not connect to live train schedules, real platform sensors, railway signalling, or a database.

## Suggested test
1. Add two trains to the normal queue and display it.
2. Allocate a platform and verify that the first train is removed.
3. Add trains with priorities 3, 1, and 2 to the priority queue.
4. Process the priority queue and verify that priority 1 is handled first.

## Author
Student mini project — edit this section to include your name, roll number, class, and institution before submission.
