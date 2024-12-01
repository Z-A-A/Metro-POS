'use client'

import { useState, useEffect } from 'react'
import SplashScreen from './SplashScreen'

export default function SplashPage() {
  const [isLoading, setIsLoading] = useState(true)

  useEffect(() => {
    const timer = setTimeout(() => {
      setIsLoading(false)
    }, 10000) // Show splash screen for 4 seconds

    return () => clearTimeout(timer)
  }, [])

  return (
    <main>
      {isLoading && <SplashScreen />}
    </main>
  )
}

