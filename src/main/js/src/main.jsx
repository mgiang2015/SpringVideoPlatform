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
import { CookiesProvider } from 'react-cookie';
import SignIn from './pages/SignIn.jsx';
import CreateCourse from './pages/CreateCourse.jsx';
import CourseEdit from './pages/CourseEdit.jsx';
import CourseContent from './pages/CourseContent.jsx';
import ChapterEdit from './pages/ChapterEdit.jsx';
import ChapterContent from './pages/ChapterContent.jsx';

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
  }, {
    path: "/signin",
    element: <SignIn />,    
  }, {
    path: "/courses/create",
    element: <CreateCourse />
  }, {
    path: "/courses/:id",
    element: <CourseContent />
  }, {
    path: "/courses/:id/edit",
    element: <CourseEdit />
  }, {
    path: "/chapters/:id/edit",
    element: <ChapterEdit />
  }, {
    path: "/chapters/:id",
    element: <ChapterContent />
  }
]);


ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <CookiesProvider defaultSetOptions={{ path: "/", maxAge: 86400 }}>
      <CssBaseline />
      <Layout>
        <RouterProvider router={router} />
      </Layout>
    </CookiesProvider>
  </React.StrictMode>,
)
