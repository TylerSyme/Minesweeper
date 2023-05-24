/** @type {import('tailwindcss').Config} */

const defaultTheme = require("tailwindcss/defaultTheme");

export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    container: {
      center: true,
    },
    extend: {
      fontFamily: {
        sans: ["Quicksand", ...defaultTheme.fontFamily.sans],
        display: ["Oxygen", ...defaultTheme.fontFamily.sans],
      },
      colors: {
        darkgray: {
          800: "#202226",
          900: "#121618",
        },
      },
    },
  },
  plugins: [],
};
