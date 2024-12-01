import React from 'react';
import { IconButton } from '@mui/material';
import LightModeIcon from '@mui/icons-material/LightMode';
import DarkModeIcon from '@mui/icons-material/DarkMode';

const DarkModeToggle = ({ darkMode, setDarkMode }) => (
  <IconButton color="inherit" onClick={() => setDarkMode(!darkMode)}>
    {darkMode ? <LightModeIcon /> : <DarkModeIcon />}
  </IconButton>
);

export default DarkModeToggle;
