import { Link } from '@mui/material';
import Box from '@mui/material/Box';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import * as React from 'react';

export default function Browse() {
    const [courses, setCourses] = React.useState([])
    React.useEffect(() => {
        axios.get('http://localhost:8080/courses')
        .then(res => {
            console.log(res.data);
            const allCourses = res.data;
            setCourses(allCourses);
        })
    }, [])

    return (
        <Box>
            {/* Hero unit */}
            <Box
            sx={{
                bgcolor: 'background.paper',
                pt: 8,
                pb: 6,
            }}
            >
                <Container maxWidth="sm">
                    <Typography
                    component="h1"
                    variant="h2"
                    align="center"
                    color="text.primary"
                    gutterBottom
                    >
                    Browse all courses
                    </Typography>
                    <Typography variant="h5" align="center" color="text.secondary" paragraph>
                    Browse through the list of courses and choose something you'd like to learn
                    </Typography>
                </Container>
            </Box>
            {/* End hero unit */}
            <Box sx={{ display: "grid", gridTemplateColumns: "auto auto auto auto", gridGap: "1rem", padding: "2rem" }}>
                {
                  courses.map(course => {
                    return (
                    <Link key={course.id} href={`/courses/${course.id}`}>
                        {course.title}
                    </Link>
                    )
                  })  
                }
            </Box>
            {/* Footer has been deleted*/}
        </Box>
    );
}