import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login'
import Home from './pages/Home'
import Signup from './pages/Signup' // <-- Naya import add kiya

function App() {
  return (
      <BrowserRouter>
        <Routes>
          {/* Agar koi seedha main link par aata hai, toh Login par bhej do */}
          <Route path="/" element={<Navigate to="/login" />} />

          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} /> {/* <-- Naya route add kiya */}
          <Route path="/home" element={<Home />} />
        </Routes>
      </BrowserRouter>
  )
}

export default App