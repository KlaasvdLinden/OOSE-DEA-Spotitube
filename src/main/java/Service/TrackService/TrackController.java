package Service.TrackService;

//@Path("/playlists/{id}/tracks")
//public class TrackController {
//
//
//    TestData testData = new TestData();
//    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getTracks(@QueryParam("token") String token) {
//        if (token.equals(testData.getTOKEN())) {
//            testData.initPlaylists();
//            testData.initTracks();
//            for (Playlist ps : testData.getPlaylists().getPlaylists()) {
//                System.out.println(testData.getPlaylists().getLength());
//                return Response.ok(ps.getTracks(), MediaType.APPLICATION_JSON).build();
//            }
//
//        }
//        return Response.status(404).build();
//
//    }
//}
