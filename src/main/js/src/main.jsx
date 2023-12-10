import React from 'react'
import ReactDOM from 'react-dom/client'
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import App from './App.jsx'
import Browse from './pages/Browse.jsx'
import Watch from './pages/Watch.jsx';
import Layout from './Layout.jsx';
import CssBaseline from '@mui/material/CssBaseline';
import Upload from './pages/Upload.jsx';
import Edit from './pages/Edit.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/browse",
    element: <Browse />,
  },
  {
    path: "/watch/:id",
    element: <Watch />,
  },{
    path: "/upload",
    element: <Upload />
  }, {
    path: "/edit/:id",
    element: <Edit />,    
  }
]);


ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <CssBaseline />
    <Layout>
      <RouterProvider router={router} />
    </Layout>
  </React.StrictMode>,
)
