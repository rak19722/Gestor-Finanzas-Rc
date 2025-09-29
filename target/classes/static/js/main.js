  document.querySelectorAll(".nav-btn").forEach(btn => {
      btn.addEventListener("click", () => {
          window.location.href = btn.dataset.url;
      });
  });

  //nav encabezado

    document.querySelectorAll(".nav-btn-encabezado").forEach(btn => {
      btn.addEventListener("click", () => {
          window.location.href = btn.dataset.url;
      });
  });