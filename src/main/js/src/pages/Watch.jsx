import { useParams } from "react-router-dom";

export default function Watch() {
    let { id } = useParams();

    return (
        <video controls width="100%">
            <source src={`http://localhost:8080/videos/stream/${id}`} type="video/mp4" />
        </video>
    )
}