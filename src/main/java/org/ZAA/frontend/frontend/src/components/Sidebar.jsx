import React from 'react';
import { List, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import DashboardIcon from '@mui/icons-material/Dashboard';
import BarChartIcon from '@mui/icons-material/BarChart';
import { Link } from 'react-router-dom';

const Sidebar = () => (
  <div style={{ width: '250px', background: '#f5f5f5', padding: '20px' }}>
    <List>
      <Link to="/" style={{ textDecoration: 'none', color: 'inherit' }}>
        <ListItem button>
          <ListItemIcon>
            <DashboardIcon />
          </ListItemIcon>
          <ListItemText primary="Dashboard" />
        </ListItem>
      </Link>
      <Link to="/sales" style={{ textDecoration: 'none', color: 'inherit' }}>
        <ListItem button>
          <ListItemIcon>
            <BarChartIcon />
          </ListItemIcon>
          <ListItemText primary="Sales" />
        </ListItem>
      </Link>
    </List>
  </div>
);

export default Sidebar;
