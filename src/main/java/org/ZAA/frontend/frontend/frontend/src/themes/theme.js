import { createTheme } from '@mui/material';

const theme = (darkMode) =>
  createTheme({
    palette: {
      mode: darkMode ? 'dark' : 'light',
      primary: {
        main: '#1976d2',
      },
      secondary: {
        main: '#d32f2f',
      },
      background: {
        default: darkMode ? '#121212' : '#f4f5f7',
        paper: darkMode ? '#1e1e1e' : '#fff',
      },
    },
    typography: {
      fontFamily: 'Roboto, Arial',
    },
  });

export default theme;
