import React, { useState, useEffect } from "react";
import { motion } from "framer-motion";
import Logo from "../assets/metro_logo.png"; // Ensure this path is correct

export default function WarpSplashScreen() {
  const [progress, setProgress] = useState(0);

  // Simulate progress bar
  useEffect(() => {
    const interval = setInterval(() => {
      setProgress((prev) => (prev >= 100 ? 100 : prev + 1));
    }, 30); // Faster simulation for a snappier feel
    return () => clearInterval(interval);
  }, []);

  return (
    <div
      style={{
        position: "relative",
        height: "100vh",
        background: "linear-gradient(135deg, #001f3f, #002b5c)", // Deep blue gradient
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        overflow: "hidden",
      }}
    >
      {/* Warp Tunnel Background Animation */}
      <motion.div
        style={{
          position: "absolute",
          top: "0",
          left: "0",
          width: "100%",
          height: "100%",
          background: "transparent",
          zIndex: -1,
        }}
        animate={{
          backgroundPosition: ["center", "0% 0%", "center"],
          backgroundSize: ["100%", "200%", "100%"],
          filter: ["blur(0px)", "blur(10px)", "blur(0px)"],
        }}
        transition={{
          duration: 10,
          repeat: Infinity,
          ease: "linear",
        }}
      >
        {/* Warp Tunnel Effect with rotating gradient */}
        <div
          style={{
            position: "absolute",
            top: "0",
            left: "0",
            width: "100%",
            height: "100%",
            background:
              "radial-gradient(circle, rgba(0, 172, 255, 0.2) 0%, rgba(0, 55, 255, 0.6) 80%)",
            animation: "warp 5s infinite linear",
            zIndex: -1,
            filter: "blur(10px)",
          }}
        ></div>
      </motion.div>

      {/* Static Logo with Soft Glow */}
      <motion.div
        style={{
          position: "relative",
          zIndex: 2,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
        initial={{ opacity: 0, y: -30 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 1.5, ease: "easeOut" }}
      >
        <img
          src={Logo}
          alt="METRO Logo"
          style={{
            width: "12rem",
            height: "auto",
            filter: "drop-shadow(0 0 15px rgba(255, 255, 255, 0.5))",
          }}
        />
      </motion.div>

      {/* Glowing Loading Bar */}
      <div
        style={{
          position: "absolute",
          bottom: "4rem",
          zIndex: 3,
          width: "60%",
        }}
      >
        <motion.div
          style={{
            width: "100%",
            backgroundColor: "rgba(255, 255, 255, 0.2)",
            height: "0.4rem",
            borderRadius: "0.5rem",
            overflow: "hidden",
            boxShadow: "0 0 15px rgba(255, 255, 255, 0.5)",
          }}
        >
          <motion.div
            style={{
              height: "100%",
              background: "linear-gradient(90deg, #FFD700, #FFA500)", // Glowing yellow gradient
              boxShadow: "0 0 8px #FFD700",
            }}
            initial={{ width: 0 }}
            animate={{ width: `${progress}%` }}
            transition={{ duration: 0.5 }}
          />
        </motion.div>
      </div>

      <style>
        {`
          @keyframes warp {
            0% {
              transform: scale(1) rotate(0deg);
              opacity: 0.7;
            }
            50% {
              transform: scale(1.5) rotate(180deg);
              opacity: 0.5;
            }
            100% {
              transform: scale(1) rotate(360deg);
              opacity: 0.7;
            }
          }
        `}
      </style>
    </div>
  );
}
