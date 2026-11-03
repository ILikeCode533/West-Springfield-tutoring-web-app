(() => {
  "use strict";

  const knownViews = ["overview", "requests", "review", "tutors", "assignments", "messages", "sessions", "subjects", "activity"];
  const names = {
    overview: "Overview",
    requests: "Help requests",
    review: "Review queue",
    tutors: "Tutor registry",
    assignments: "Assignments",
    messages: "Conversations",
    sessions: "Sessions",
    subjects: "Subjects & sectors",
    activity: "Activity log"
  };
  const panels = [...document.querySelectorAll("[data-view]")];
  const navLinks = [...document.querySelectorAll("[data-nav]")];
  const breadcrumb = document.getElementById("breadcrumb-current");

  function routeView() {
    const queryView = new URLSearchParams(window.location.search).get("view");
    if (queryView && knownViews.includes(queryView)) return queryView;
    const hashView = window.location.hash.replace("#", "");
    return knownViews.includes(hashView) ? hashView : "overview";
  }

  function showView(view, updateLocation = false) {
    const selected = knownViews.includes(view) ? view : "overview";
    panels.forEach((panel) => {
      const active = panel.dataset.view === selected;
      panel.hidden = !active;
      panel.classList.toggle("is-active", active);
    });
    navLinks.forEach((link) => {
      const active = link.dataset.nav === selected;
      link.classList.toggle("is-current", active);
      if (active) link.setAttribute("aria-current", "page");
      else link.removeAttribute("aria-current");
    });
    if (breadcrumb) breadcrumb.textContent = names[selected];
    document.title = `${names[selected]} · Tutoring Club`;
    if (updateLocation) {
      const nextUrl = selected === "overview" ? "/" : `/?view=${encodeURIComponent(selected)}`;
      window.history.pushState({ view: selected }, "", nextUrl);
    }
    window.scrollTo({ top: 0, behavior: window.matchMedia("(prefers-reduced-motion: reduce)").matches ? "instant" : "smooth" });
  }

  document.addEventListener("click", (event) => {
    const navLink = event.target.closest("a[data-nav]");
    if (!navLink || event.defaultPrevented || event.button !== 0 || event.metaKey || event.ctrlKey || event.shiftKey || event.altKey) return;
    event.preventDefault();
    showView(navLink.dataset.nav, true);
  });
  window.addEventListener("popstate", () => showView(routeView()));

  const previewBanner = document.querySelector(".preview-banner");
  const dismissBanner = document.querySelector(".banner-dismiss");
  if (dismissBanner && previewBanner) {
    dismissBanner.addEventListener("click", () => {
      previewBanner.hidden = true;
    });
  }

  const dialog = document.getElementById("action-dialog");
  const dialogContent = document.getElementById("dialog-content");
  const dialogResult = document.getElementById("dialog-result");
  let lastOpener = null;
  function closeDialog() {
    if (dialog && dialog.open) dialog.close();
  }
  function openDialog(templateId, opener) {
    const template = document.getElementById(templateId);
    if (!template || !dialog || !dialogContent || !dialogResult) return;
    lastOpener = opener || null;
    dialogContent.replaceChildren(template.content.cloneNode(true));
    dialogResult.hidden = true;
    dialogResult.textContent = "";
    dialog.showModal();
    const firstField = dialogContent.querySelector("input, select, textarea");
    if (firstField) firstField.focus({ preventScroll: true });
  }

  document.addEventListener("click", (event) => {
    const opener = event.target.closest("[data-open-dialog]");
    if (opener) openDialog(opener.dataset.openDialog, opener);
    if (event.target.closest("[data-close-dialog]")) closeDialog();
  });
  if (dialog) {
    dialog.addEventListener("click", (event) => {
      if (event.target === dialog) closeDialog();
    });
    dialog.addEventListener("close", () => {
      if (lastOpener?.isConnected) lastOpener.focus();
      lastOpener = null;
    });
    dialog.addEventListener("submit", (event) => {
      if (!event.target.matches(".preview-form")) return;
      event.preventDefault();
      if (!event.target.reportValidity()) return;
      const form = event.target;
      form.classList.add("is-complete");
      [...form.elements].forEach((element) => { element.disabled = true; });
      const title = form.querySelector("h2")?.textContent || "Form";
      if (dialogResult) {
        dialogResult.textContent = `${title} checked. This is a front-end preview only — nothing was saved, sent, assigned, or changed.`;
        dialogResult.hidden = false;
      }
      if (dialogResult) dialogResult.scrollIntoView({ block: "nearest", behavior: "smooth" });
    });
  }

  // Status chips only filter the intentionally empty preview collection.
  document.querySelectorAll("[data-filter]").forEach((button) => {
    button.addEventListener("click", () => {
      const group = button.parentElement;
      group.querySelectorAll("[data-filter]").forEach((chip) => {
        chip.classList.toggle("is-selected", chip === button);
        chip.setAttribute("aria-pressed", String(chip === button));
      });
      const result = group.parentElement.querySelector(".result-count");
      if (result) result.textContent = button.dataset.filter === "all" ? "Queue is clear" : `No ${button.textContent.trim().toLowerCase()} items`;
    });
  });

  document.querySelectorAll("[data-empty-filter]").forEach((control) => {
    control.addEventListener("input", () => {
      const toolbar = control.closest(".toolbar");
      const count = toolbar?.querySelector(".result-count");
      const query = control.value.trim();
      if (!count) return;
      if (!count.dataset.defaultText) count.dataset.defaultText = count.textContent;
      count.textContent = query ? "0 matches" : count.dataset.defaultText;
    });
    control.addEventListener("change", () => {
      const toolbar = control.closest(".toolbar");
      const count = toolbar?.querySelector(".result-count");
      if (!count) return;
      if (!count.dataset.defaultText) count.dataset.defaultText = count.textContent;
      count.textContent = control.value ? "0 matches" : count.dataset.defaultText;
    });
  });
  document.querySelectorAll("[data-filter]").forEach((button) => {
    button.setAttribute("aria-pressed", String(button.classList.contains("is-selected")));
  });

  document.querySelectorAll(".login-form").forEach((form) => {
    form.addEventListener("submit", (event) => {
      event.preventDefault();
      if (!form.reportValidity()) return;
      const result = document.querySelector(".login-result");
      if (result) {
        result.textContent = "Sign-in is not connected in this preview. Your credentials were not sent or saved.";
        result.hidden = false;
      }
    });
  });

  showView(routeView());
})();
