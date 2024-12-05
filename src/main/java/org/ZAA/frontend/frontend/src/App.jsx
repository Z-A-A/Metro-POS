import { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, useNavigate } from 'react-router-dom';
// import CssBaseline from '@mui/material/CssBaseline';
// import ThemeProvider from '@mui/material/styles';
import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';
import Dashboard from './pages/Dashboard';
import SuperAdmin from './pages/SuperAdmin';
import DataEntryOperatorPage from './pages/DataEntryOperatorPage';
import CashierPage from './pages/CashierPage';
import LoginPage from './pages/LoginPage';
import SplashPage from './pages/SplashPage';
import RoleSelection from './pages/RoleSelection';
import BranchManager from './pages/BranchManagerPage';
import theme from './themes/theme';
import { ThemeProvider, CssBaseline } from '@mui/material';

function App() {
  const [darkMode, setDarkMode] = useState(true);

  return (
    // <ThemeProvider theme={theme}>
      // <CssBaseline />
      <Router>
        <Routes>
          <Route path="/" element={<SplashPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/superadmin" element={<SuperAdmin />} />
          <Route path="/dataentryoperator" element={<DataEntryOperatorPage />} />
          <Route path="/RoleSelection" element={<RoleSelection />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/cashier" element={<CashierPage />} />
          <Route path="/branchmanager" element={<BranchManager />} />

          {/* Add other routes as needed */}
        </Routes>
      </Router>
    // </ThemeProvider>
  );
}

export default App;