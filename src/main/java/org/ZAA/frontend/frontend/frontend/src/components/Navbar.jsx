import React from 'react';
import { AppBar, Toolbar, Typography, IconButton } from '@mui/material';
import DarkModeToggle from './DarkModeToggle';

const Navbar = ({ darkMode, setDarkMode }) => (
  <AppBar position="static" style={{ backgroundColor: '#1976d2' }}>
    <Toolbar>
      <Typography variant="h6" style={{ flexGrow: 1 }}>
        Supermarket Admin Dashboard
      </Typography>
      <DarkModeToggle darkMode={darkMode} setDarkMode={setDarkMode} />
    </Toolbar>
  </AppBar>
);

export default Navbar;
