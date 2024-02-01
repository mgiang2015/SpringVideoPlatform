import PlayLessonIcon from '@mui/icons-material/PlayLesson';
import { Box, Divider, Typography } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Sidebar from "../components/Sidebar";
import VideoCard from "../components/VideoCard";

export default function ChapterContent() {
    let { courseId, chapterId } = useParams();
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [chapters, setChapters] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/chapters/${chapterId}`)
        .then(res => {
            console.log(res.data);
            setTitle(res.data.title);
            setDescription(res.data.description);
        })

        axios.get(`http://localhost:8080/courses/${courseId}`)
        .then(res => {
            console.log(res.data);
            setChapters(res.data.chapters);
        })
    }, [])

    const items = chapters.map(chapter => {
        return {
            icon: <PlayLessonIcon />,
            text: chapter.title,
            href: `/courses/${courseId}/chapters/${chapter.id}`
        }
    })

    return (
        <Box sx={{ height: "100%", display: "grid", gridTemplateColumns: "1fr 5fr", gridGap: "0rem", padding: "1rem" }}>
            {/* Sidebar */}
            <Sidebar items={items} />
            <Box sx={{ height: "100%", width: "100%", display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "auto" }}>
                <Typography gutterBottom variant="h3">{title}</Typography>
                <Divider />
                <Typography gutterBottom>{description}</Typography>
                <VideoCard videoName={title} videoUrl={`http://localhost:8080/chapters/${chapterId}/stream`} />
            </Box>
        </Box>
    )
}