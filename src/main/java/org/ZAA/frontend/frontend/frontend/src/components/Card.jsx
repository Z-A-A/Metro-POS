import React from 'react';
import { Card, CardContent, Typography } from '@mui/material';
import { motion } from 'framer-motion';

const DashboardCard = ({ title, value }) => (
  <motion.div whileHover={{ scale: 1.05 }} style={{ marginBottom: '20px' }}>
    <Card>
      <CardContent>
        <Typography variant="h6">{title}</Typography>
        <Typography variant="h4">{value}</Typography>
      </CardContent>
    </Card>
  </motion.div>
);

export default DashboardCard;
