import { Card, CardContent, CardMedia, Typography } from "@mui/material";

export default function VideoCard({ videoName, videoUrl }) {
    return (
        <Card sx={{ maxHeight: "80%", maxWidth: "80%" }}>
            <CardMedia
                component="video"
                src={videoUrl}
                controls
            />
        </Card>
    )
}