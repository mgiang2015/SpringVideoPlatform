import CssBaseline from '@mui/material/CssBaseline';
import React from 'react';
import { CookiesProvider } from 'react-cookie';
import ReactDOM from 'react-dom/client';
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import App from './App.jsx';
import Layout from './Layout.jsx';
import Browse from './pages/Browse.jsx';
import ChapterContent from './pages/ChapterContent.jsx';
import ChapterEdit from './pages/ChapterEdit.jsx';
import CourseContent from './pages/CourseContent.jsx';
import CourseEdit from './pages/CourseEdit.jsx';
import CourseManagement from './pages/CourseManagement.jsx';
import CreateCourse from './pages/CreateCourse.jsx';
import SignIn from './pages/SignIn.jsx';

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
  },
  {
    path: "/browse",
    element: <Browse />,
  }, {
    path: "/signin",
    element: <SignIn />,    
  }, {
    path: "/courses/create",
    element: <CreateCourse />
  }, {
    path: "/courses/:courseId",
    element: <CourseContent />
  }, {
    path: "/courses/:courseId/edit",
    element: <CourseEdit />
  }, {
    path: "/courses/:courseId/chapters/:chapterId/edit",
    element: <ChapterEdit variant={"edit"}/>
  }, {
    path: "/courses/:courseId/chapters/:chapterId",
    element: <ChapterContent />
  }, {
    path: "/courses/:courseId/chapters/new",
    element: <ChapterEdit variant={"new"}/>
  }, {
    path: "/courses/management",
    element: <CourseManagement />
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
