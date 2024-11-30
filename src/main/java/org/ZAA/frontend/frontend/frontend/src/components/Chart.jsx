import React from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';
import { salesData } from '../mockData';

 export default function Chart(){
return(
  <ResponsiveContainer width="100%" height={300}>
    <LineChart data={salesData}>
      <CartesianGrid stroke="#ccc" />
      <XAxis dataKey="name" />
      <YAxis />
      <Tooltip />
      <Line type="monotone" dataKey="sales" stroke="#8884d8" />
    </LineChart>
  </ResponsiveContainer>
);
}

