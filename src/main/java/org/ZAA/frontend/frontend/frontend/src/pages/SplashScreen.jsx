import { motion, AnimatePresence } from 'framer-motion';
import { useState, useEffect } from 'react';
import Logo from '../assets/metro_logo.png';

// Grocery items with emojis
const groceryItems = [
  { name: 'apple', emoji: '🍎' },
  { name: 'banana', emoji: '🍌' },
  { name: 'bread', emoji: '🍞' },
  { name: 'milk', emoji: '🥛' },
  { name: 'cheese', emoji: '🧀' },
  { name: 'carrot', emoji: '🥕' },
  { name: 'broccoli', emoji: '🥦' },
  { name: 'pizza', emoji: '🍕' },
  { name: 'egg', emoji: '🥚' },
  { name: 'tomato', emoji: '🍅' },
  { name: 'orange', emoji: '🍊' },
  { name: 'watermelon', emoji: '🍉' },
];

export default function SplashScreen() {
  const [items, setItems] = useState([]);
  const [progress, setProgress] = useState(0);
  const [isExiting, setIsExiting] = useState(false);

  useEffect(() => {
    const totalDuration = 10000; // 10 seconds for the splash screen
    const progressInterval = setInterval(() => {
      setProgress((prev) => {
        if (prev >= 100) {
          clearInterval(progressInterval);
          setIsExiting(true);
          return 100;
        }
        return prev + 1;
      });
    }, totalDuration / 100);

    // Add grocery items at intervals
    const itemsInterval = setInterval(() => {
      const selectedItem = groceryItems[Math.floor(Math.random() * groceryItems.length)];
      const angle = Math.random() * 360; // Random angle for circular motion
      setItems((prevItems) => [
        ...prevItems,
        {
          id: Math.random(),
          item: selectedItem,
          angle, // Initial angle for circular path
        },
      ]);
    }, 400); // New item every 400ms

    return () => {
      clearInterval(progressInterval);
      clearInterval(itemsInterval);
    };
  }, []);

  return (
    <motion.div
      className="relative flex items-center justify-center min-h-screen overflow-hidden bg-[#002B5C]"
      animate={{ opacity: isExiting ? 0 : 1 }}
      transition={{ duration: 0.5 }}
    >
      {/* Grocery items animation */}
      <AnimatePresence>
        {items.map(({ id, item, angle }) => (
          <motion.div
            key={id}
            className="absolute text-4xl z-30"
            initial={{
              x: 0, // Start at the center horizontally
              y: window.innerHeight, // Start below the screen
            }}
            animate={{
              x: [
                0,
                150 * Math.cos((angle * Math.PI) / 180), // Circular motion x-coordinate
                0,
              ],
              y: [
                window.innerHeight, // Start at the bottom
                150 * Math.sin((angle * Math.PI) / 180), // Circular motion y-coordinate
                0,
              ],
            }}
            exit={{ opacity: 0 }}
            transition={{
              duration: 3, // Time for the full animation
              ease: 'easeInOut', // Smooth motion
              repeat: Infinity, // Repeat indefinitely for circular motion
            }}
            onAnimationComplete={() => {
              setItems((prevItems) => prevItems.filter((i) => i.id !== id));
            }}
          >
            {item.emoji}
          </motion.div>
        ))}
      </AnimatePresence>

      {/* Logo */}
      <motion.div
        className="relative z-20"
        initial={{ scale: 0.5, opacity: 0 }}
        animate={{ scale: 1, opacity: 1 }}
        transition={{ duration: 0.8, ease: 'easeOut' }}
      >
        <motion.div
          animate={{ y: [0, -10, 0] }}
          transition={{
            duration: 2,
            repeat: Infinity,
            ease: 'easeInOut',
          }}
          className="relative w-64 h-32 md:w-96 md:h-48"
        >
          <img src={Logo} alt="METRO Logo" layout="fill" priority="true" />
        </motion.div>
      </motion.div>

      {/* Progress Bar */}
      <div className="absolute bottom-20 z-30 w-64">
        <motion.div
          className="w-full bg-[#FFD700]/20 h-2 rounded-full overflow-hidden"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.5 }}
        >
          <motion.div
            className="h-full bg-[#FFD700]"
            initial={{ width: 0 }}
            animate={{ width: `${progress}%` }}
            transition={{ duration: 0.5 }}
          />
        </motion.div>
        <motion.div
          className="flex items-center justify-between mt-4 text-[#FFD700]"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.5 }}
        >
          <span className="text-xl font-semibold">Loading</span>
          <span className="text-xl font-semibold">{progress}%</span>
        </motion.div>
      </div>

      {/* Radial Gradient Overlay */}
      <div className="absolute inset-0 bg-gradient-radial from-transparent via-transparent to-[#002B5C] pointer-events-none z-40" />
    </motion.div>
  );
}
