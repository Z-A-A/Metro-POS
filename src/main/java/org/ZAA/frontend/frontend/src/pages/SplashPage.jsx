'use client'

import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import SplashScreen from './SplashScreen'


export default function SplashPage() {
  const [isLoading, setIsLoading] = useState(true)
  const navigate = useNavigate()

  useEffect(() => {
    const timer = setTimeout(() => {
      setIsLoading(false);
      navigate('/RoleSelection');
    }, 10000) 

    return () => clearTimeout(timer)
  }, [navigate])

  return (
    <main>
      {isLoading && <SplashScreen />}
    </main>
  )
}

