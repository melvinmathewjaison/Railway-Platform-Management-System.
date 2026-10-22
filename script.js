const normalQueue = [];
const priorityQueue = [];
let sequence = 0;

const form = document.querySelector("#train-form");
const message = document.querySelector("#message");

form.addEventListener("submit", (event) => {
  event.preventDefault();
  const number = document.querySelector("#train-number").value.trim();
  const name = document.querySelector("#train-name").value.trim();
  const type = document.querySelector("#queue-type").value;
  const priority = Number(document.querySelector("#priority").value);

  if (!number || !name) {
    message.textContent = "Please enter both train number and train name.";
    return;
  }

  const train = { number, name, priority, sequence: sequence++ };
  if (type === "normal") {
    normalQueue.push(train);
    message.textContent = `Train ${number} added to the normal FIFO queue.`;
  } else {
    priorityQueue.push(train);
    priorityQueue.sort((a, b) => a.priority - b.priority || a.sequence - b.sequence);
    message.textContent = `Train ${number} added to the priority queue.`;
  }
  form.reset();
  render();
});

document.querySelector("#process-normal").addEventListener("click", () => {
  const train = normalQueue.shift();
  message.textContent = train
    ? `Platform allocated to ${train.number} — ${train.name}.`
    : "The normal queue is empty.";
  render();
});

document.querySelector("#process-priority").addEventListener("click", () => {
  const train = priorityQueue.shift();
  message.textContent = train
    ? `Highest-priority train processed: ${train.number} — ${train.name}.`
    : "The priority queue is empty.";
  render();
});

function renderList(elementId, trains, isPriority = false) {
  const list = document.querySelector(elementId);
  list.replaceChildren();
  const displayItems = isPriority ? trains : [...trains];
  for (const train of displayItems) {
    const item = document.createElement("li");
    const priorityLabel = ["", "High", "Medium", "Normal"][train.priority];
    item.textContent = `${train.number} — ${train.name}${isPriority ? ` (Priority: ${priorityLabel})` : ""}`;
    list.appendChild(item);
  }
  if (trains.length === 0) {
    const item = document.createElement("li");
    item.textContent = "No trains waiting.";
    list.appendChild(item);
  }
}

function render() {
  renderList("#normal-list", normalQueue);
  renderList("#priority-list", priorityQueue, true);
  document.querySelector("#normal-count").textContent = normalQueue.length;
  document.querySelector("#priority-count").textContent = priorityQueue.length;
}
render();
