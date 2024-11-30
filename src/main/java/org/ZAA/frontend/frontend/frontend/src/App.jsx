import { useState } from 'react'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import CashierPage from './pages/CashierPage'
import LoginPage from './pages/LoginPage'
import DataEntryOperatorPage from './pages/DataEntryOperatorPage'
import SplashPage from './pages/SplashPage'
import './App.css'
import Sales from './pages/Sales';
import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';
import Dashboard from './pages/Dashboard';
import theme from './themes/theme';
import { CssBaseline, ThemeProvider } from '@mui/material';

function App() {
  const [count, setCount] = useState(0)
  const [darkMode, setDarkMode] = useState(false);

  return (
   <>
      {/* <Dashboard /> */}
      <LoginPage />
   </>
  )
}

export default App
