import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { useCookies } from 'react-cookie'

function App() {
  return (
    <>
      <h1>Learning Management System</h1>
      <div className="card">
        <p>
          Basic Learning Management System written using Java Springboot and React.
        </p>
        <p>
          Uses MongoDB and MySQL databases.
        </p>
      </div>
    </>
  )
}

export default App
