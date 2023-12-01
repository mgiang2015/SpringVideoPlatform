import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)
  const [fileUrl, setFileUrl] = useState("")
  useEffect(() => {
    // simple GET request
    fetch("http://localhost:8080/videos/stream/6569ecca915fa5113771bf87")
      .then((response) => response.blob()) // create object url for response
      .then((blob) => {
        blob = blob.slice(0, blob.size, "video/mp4")
        console.log(blob);
        return URL.createObjectURL(blob)
      })
      .then((url) => {
        setFileUrl(url);
        console.log("Done!")
      })
  }, [])

  return (
    <>
      <video controls width="100%">
        <source src={fileUrl} type="video/mp4"/>
      </video>
      <p>{fileUrl}</p>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App
