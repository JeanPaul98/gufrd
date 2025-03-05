/** @type {import('tailwindcss').Config} */
module.exports = {
  prefix:"tw-",
  content: [
    "./src/**/*.{html,ts}",
    "./projects/**/*.{html,ts}",
  ],
  theme: {
    fontFamily: {
      sans: ["Inter var", "Arial", "sans-serif"],
      mono: ["Source Code Pro", "monospace"],
    },

    extend: {
      fontFeatureSettings: {
        DEFAULT: '"cv02", "cv03", "cv04", "cv11"',
      },
      boxShadow: {
        'button': '0px 1px 1px 0px rgba(0, 0, 0, 0.12), 0px 2px 5px 0px rgba(60, 66, 87, 0.08)',
        'button-primary': 'inset 0px -1px 1px 0px rgba(0, 0, 0, 0.12), 0px 1px 1px 0px rgba(0, 0, 0, 0.08), 0px 2px 5px 0px rgba(60, 66, 87, 0.12)',
      },
      transitionProperty: {
        'width': 'width'
      },

      colors: {
        transparent: 'transparent',
        current: 'currentColor',
        'white': 'var(--faw-raw--white)',
        'secondary': 'var(--faw-raw--secondary)',
        'fg-secondary': 'var(--faw-raw--fg-secondary)',
        'secondary-light': 'var(--faw-raw--secondary-light)',
        'border-secondary': 'var(--faw-raw--border-secondary)',
        'primary': 'var(--faw-raw--primary)',
        'metal': 'var(--faw-raw--metal)',
        'tahiti': 'var(--faw-raw--tahiti)',
        'silver': 'var(--faw-raw--silver)',
        'bubble-gum': 'var(--faw-raw--bubble-gum)',
        'bermuda': 'var(--faw-raw--bermuda)',
        'fg-accent-green': 'var(--faw-raw-fg-accent-green)',
        'bg-accent-light-green': 'var(--faw-raw-bg-accent-light-green)',
        'fg-accent-red': 'var(--faw-raw-fg-accent-red)',
        'bg-accent-light-red': 'var(--faw-raw-bg-accent-light-red)',
        'fg-accent-yellow': 'var(--faw-raw-fg-accent-yellow)',
        'bg-accent-light-yellow': 'var(--faw-raw-bg-accent-light-yellow)',
        'fg-accent-blue': 'var(--faw-raw-fg-accent-blue)',
        'bg-accent-light-blue': 'var(--faw-raw-bg-accent-light-blue)',
      },
      animation: {
        "gradient-skeleton": "gradient-animation 1.5s infinite",
        progress: 'progress 1s infinite linear',
      },

      keyframes: {
        "gradient-animation": {
          "0%": { "background-position": "200% 0" },
          "100%": { "background-position": "-200% 0" },
        },
        "shimmer": {
          "100%": { "transform": "translateX(100%)", },
        },
        progress: {
          '0%': { transform: ' translateX(0) scaleX(0)' },
          '40%': { transform: 'translateX(0) scaleX(0.4)' },
          '100%': { transform: 'translateX(100%) scaleX(0.5)' },
        },
      },
      transformOrigin: {
        'left-right': '0% 50%',
      },
      backgroundSize: {
        "200%": "200%",
      },


    },
  },
  plugins: [
    require('tailwindcss'),
    require('autoprefixer'),
    function ({ addBase }) {
      addBase({
        '*': {
          fontFeatureSettings: '"cv02", "cv03", "cv04", "cv11" on !important',
        },
      });
    },
  ],
}

