import { Card, CardContent, CardMedia, Container, Typography } from "@mui/material";
import { useParams } from "react-router-dom";
import VideoCard from "../components/VideoCard";

export default function Watch() {
    let { id } = useParams();
    
    return (
        <Container maxWidth="lg">
            <VideoCard videoName={id} videoUrl={`http://localhost:8080/videos/stream/${id}`} />
        </Container>
    )
}